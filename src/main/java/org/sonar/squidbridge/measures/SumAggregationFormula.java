/*
 * SSLR Squid Bridge
 * Copyright (C) 2010 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.squidbridge.measures;

import java.util.Collection;

public class SumAggregationFormula implements AggregationFormula {

  @Override
  public double aggregate(MetricDef metric, Collection<Measurable> measurables) {
    double aggregation = 0;
    for (Measurable measurable : measurables) {
      aggregation += measurable.getDouble(metric);
    }
    return aggregation;
  }

}
